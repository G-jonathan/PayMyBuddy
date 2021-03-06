package com.openclassroomsproject.paymybuddy.configuration.security;

import com.openclassroomsproject.paymybuddy.userInterface.login.LoginView;
import com.openclassroomsproject.paymybuddy.userInterface.registration.RegistrationForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> {
			final UI ui = uiEvent.getUI();
			ui.addBeforeEnterListener(this::authenticateNavigation);
		});
	}

	private void authenticateNavigation(BeforeEnterEvent event) {
		if (!LoginView.class.equals(event.getNavigationTarget())
				&& !RegistrationForm.class.equals(event.getNavigationTarget())
				&& !SecurityUtils.isUserLoggedIn()) {
			event.rerouteTo(LoginView.class);
		}
	}
}