package org.nabius.sellcars.services;

import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.UserDto;
import org.nabius.sellcars.entity.Car;
import org.nabius.sellcars.entity.User;
import org.nabius.sellcars.Dto.CarDto;
import org.nabius.sellcars.enums.UserType;
import org.nabius.sellcars.mappers.CarMapper;
import org.nabius.sellcars.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarsService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final UserService userService;
    private final AuthService authService;


    public CarDto addCar(Long userId, CarDto carDto) {
        User user = this.userService.findUserById(userId);
        if ((user.getType() == UserType.BASIC) && (user.getCars().isEmpty())){
            throw new IllegalStateException("Basic user can sell only 1 car. Please upgrade to PREMIUM");
        }
        Car car = this.carMapper.mapToEntity(carDto);
        car.setUser(user);
        return this.carMapper.mapToDto(this.carRepository.save(car));
    }

    public void deleteCar(Long carId) {
        if (!this.carRepository.existsById(carId)) {
            throw new IllegalArgumentException("Car with id " + carId + " does not exist");
        }
        this.carRepository.deleteById(carId);
    }

    public Optional<CarDto> findCar(Long carId) {
        UserDto userDto = authService.getUserDetailsCurrentUser();
        if (userDto.getType()==UserType.BASIC) {
            return this.carRepository.findById(carId).map(this.carMapper::mapToDto);
        }
        return this.carRepository.findById(carId).map(this.carMapper::mapToDto);
    }

    public List<CarDto> findCars() {
        return this.carRepository.findAll().
                stream().
                map(this.carMapper::mapToDto)
                .toList();
    }

    @Transactional
    public Optional<CarDto> modifyCar(Long carId, CarDto carDto) {
        return this.carRepository.findById(carId)
                .map(car -> this.carMapper.update(carDto, car))
                .map(this.carMapper::mapToDto);
    }

    @Transactional
    public Optional<CarDto> modifyCarPartially(Long carId, CarDto carDto) {
        return this.carRepository.findById(carId)
                .map(car -> this.carMapper.partialUpdate(carDto, car))
                .map(carMapper::mapToDto);
    }
}