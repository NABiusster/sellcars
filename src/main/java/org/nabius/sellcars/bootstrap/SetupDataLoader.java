package org.nabius.sellcars.bootstrap;

import jakarta.transaction.Transactional;
import org.nabius.sellcars.entity.Currency;
import org.nabius.sellcars.entity.Role;
import org.nabius.sellcars.entity.User;
import org.nabius.sellcars.repository.CurrencyRepository;
import org.nabius.sellcars.repository.RoleRepository;
import org.nabius.sellcars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createCurrencyIfNotFound(978, "EUR", "€");
        createCurrencyIfNotFound(840, "USD", "$");

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_MANAGER");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow();
        User adminUser = userRepository.findByUsername("Admin").orElseGet(
                () -> {
                    User user = new User();
                    user.setFullName("Admin");
                    user.setUsername("Admin");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setEmail("admin@test.com");
                    user.setRoles(List.of(adminRole));
                    userRepository.save(user);
                    return user;
                }
        );
        alreadySetup = true;
    }

    @Transactional
    void createCurrencyIfNotFound(int code, String name, String symbol) {
        Currency currency = currencyRepository.findByCode(code).orElseGet(() -> {
            Currency addCurrency = new Currency();
            addCurrency.setCode(code);
            addCurrency.setName(name);
            addCurrency.setSymbol(symbol);
            return currencyRepository.save(addCurrency);
        });
    }

    @Transactional
    void createRoleIfNotFound(
            String name) {
        Role role = roleRepository.findByName(name).orElseGet(() -> {
                    Role addRole = new Role();
                    addRole.setName(name);
                    roleRepository.save(addRole);
                    return addRole;
                }
        );

    }
}
