package com.poncho.flowabledemo.Services;

import com.poncho.flowabledemo.engineJpaDatabase.model.AccessControl;
import com.poncho.flowabledemo.engineJpaDatabase.model.Material;
import org.flowable.idm.api.User;

import java.util.Set;

public interface RequestAccessControlService {
    String startAccessControlRequest(AccessControl accessControlRequest, User user, Set<Material> materiales);
    User getFlowUser(String username);
    void logAccessControlRequest(AccessControl accessControl);
}
