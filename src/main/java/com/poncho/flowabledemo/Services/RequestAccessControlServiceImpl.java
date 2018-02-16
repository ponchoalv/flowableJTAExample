package com.poncho.flowabledemo.Services;

import com.poncho.flowabledemo.engineJpaDatabase.model.AccessControl;
import com.poncho.flowabledemo.engineJpaDatabase.model.Material;
import com.poncho.flowabledemo.engineJpaDatabase.repositories.EngineAccessControlRepository;
import com.poncho.flowabledemo.engineJpaDatabase.repositories.EngineMaterialRepository;
import com.poncho.flowabledemo.secondDatabaseExample.repositories.SecondAccessControlRepository;
import org.flowable.engine.*;
import org.flowable.idm.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
public class RequestAccessControlServiceImpl implements RequestAccessControlService {

    private final RuntimeService runtimeService;

    private final RepositoryService repositoryService;

    private final IdentityService identityService;

    private final EngineAccessControlRepository engineAccessControlRepository;

    private final SecondAccessControlRepository secondSecondAccessControlRepository;


    private final EngineMaterialRepository engineMaterialRepository;

    private final Logger logger = LoggerFactory.getLogger(RequestAccessControlServiceImpl.class);


    @Autowired
    public RequestAccessControlServiceImpl(RuntimeService runtimeService, RepositoryService repositoryService,
                                           IdentityService identityService, EngineAccessControlRepository engineAccessControlRepository, EngineMaterialRepository engineMaterialRepository,
                                           com.poncho.flowabledemo.secondDatabaseExample.repositories.SecondAccessControlRepository secondSecondAccessControlRepository) {
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
        this.identityService = identityService;
        this.engineAccessControlRepository = engineAccessControlRepository;
        this.engineMaterialRepository = engineMaterialRepository;
        this.secondSecondAccessControlRepository = secondSecondAccessControlRepository;
    }


    @Override
    @Transactional
    public String startAccessControlRequest(AccessControl accessControlRequest, User user, Set<Material> materiales) {

        for (Material material: materiales) {
            material.setAccessControl(accessControlRequest);
            accessControlRequest.getMaterialesIngresa().add(material);
        }

        saveAccessControl(accessControlRequest);

        engineMaterialRepository.save(materiales);

        String procId = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("dataCenterAccessProcess")
                .latestVersion().singleResult().getId();

        Map<String, Object> variables = new HashMap<>();
        variables.put("accessControlRequest", accessControlRequest);
        variables.put("mail", user.getEmail());
        variables.put("initiator", user.getId());

        com.poncho.flowabledemo.secondDatabaseExample.model.AccessControl accs = new com.poncho.flowabledemo.secondDatabaseExample.model.AccessControl();
        accs.setApellido("peiiiiiitooo");
        accs.setNombre("sorpetitoooo");
        accs.setAutoriza("poncho");
        accs.setDni("314445643");

        secondSecondAccessControlRepository.save(accs);

        return runtimeService.startProcessInstanceById(procId, variables).getId();
        //return "creado";
    }

    private void saveAccessControl(AccessControl accessControl){
        engineAccessControlRepository.save(accessControl);
    }

    @Override
    public User getFlowUser(String username) {
        return identityService.createUserQuery().userId(username).singleResult();
    }

    @Override
    @Transactional
    public void logAccessControlRequest(AccessControl accessControl) {
        logger.debug(accessControl.toString());
    }

    public void logSomething() {
        logger.debug("something");
    }
}
