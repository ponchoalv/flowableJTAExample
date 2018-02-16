package com.poncho.flowabledemo.controllers;

import com.poncho.flowabledemo.engineJpaDatabase.model.AccessControl;
import com.poncho.flowabledemo.engineJpaDatabase.model.Material;
import com.poncho.flowabledemo.Services.RequestAccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class AccessControlController {

    private final RequestAccessControlService requestAccessControlService;


    @Autowired
    public AccessControlController(RequestAccessControlService requestAccessControlService) {
        this.requestAccessControlService = requestAccessControlService;
    }


    @RequestMapping(value = "/accessControlRequest")
    public String requestControlDeAccesoForm(@AuthenticationPrincipal final User user, final Model model) {

        AccessControl accessControl = new AccessControl();

        model.addAttribute("user", user);
        model.addAttribute("accessControl", accessControl);


        return "cargarSolicitudDeAcceso";
    }


    @RequestMapping(value = "/accessControlRequest", method = RequestMethod.POST)
    public String requestAccessControlToEngine(@AuthenticationPrincipal User user,
                                               @ModelAttribute AccessControl accessControl, Model model) {

        org.flowable.idm.api.User flowUser = requestAccessControlService.getFlowUser(user.getUsername());

        Set<Material> materiales = new LinkedHashSet<>();

        materiales.add(new Material("Cables"));
        materiales.add(new Material("Pinzas"));
        materiales.add(new Material("Destornillador"));

        accessControl.setAutoriza(user.getUsername());

        requestAccessControlService.startAccessControlRequest(accessControl, flowUser, materiales);

        model.addAttribute("user", user);
        model.addAttribute("accessControlRequest", accessControl);

        return "accessControlForm";
    }


}
