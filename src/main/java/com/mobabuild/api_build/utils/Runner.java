package com.mobabuild.api_build.utils;

import com.fasterxml.jackson.databind.introspect.AnnotatedAndMetadata;
import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.repository.AuthorityRepository;
import com.mobabuild.api_build.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.authorityRepository.count() == 0){
            this.authorityRepository.saveAll(List.of(
                    new Authority(AuthorityName.ADMIN),
                    new Authority(AuthorityName.READ),
                    new Authority(AuthorityName.WRITE)
            ));
        }

        if(this.userRepository.count()==0){
            this.userRepository.saveAll(List.of(
                    new User(
                            "jmartinlunasescobar@gmail.com",
                            "Javi",
                            "javi1234",
                            List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())
                    ),
                    new User(
                            "usuarioRead@gmail.com",
                            "usuarioRead",
                            "UsuarioReadPass1",
                            List.of(this.authorityRepository.findByName(AuthorityName.READ).get())
                    ),
                    new User(
                            "usuarioReadWrite@gmail.com",
                            "usuarioReadWrite",
                            "UsuarioReadWritePass1",
                            List.of(
                                    this.authorityRepository.findByName(AuthorityName.READ).get(),
                                    this.authorityRepository.findByName(AuthorityName.WRITE).get()
                            )
                    )
            ));
        }
    }
}
