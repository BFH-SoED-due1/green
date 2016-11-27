/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.view;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.ti.soed.hs16.srs.green.controller.MyUIControllers;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

	private Button register;
	private TextField name;
	private TextField email;
	private TextField pw;
	private MyUIControllers controller = new MyUIControllers();

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	public class Reserve extends GridLayout {

		Reserve() {
			super(6, controller.getAmountRooms() + 5);
			System.out.println(controller.getAmountRooms());
			Label label = new Label(name.getValue());
			addComponent(label, 0, 0, 5, 0);
			InlineDateField date = new InlineDateField();
			date.setResolution(DateField.RESOLUTION_DAY);
			addComponent(date, 0, 1, 3, 3);
			addComponent(new Label("Time: "), 4, 1, 5, 1);
			addComponent(new Label("From: "), 4, 2);
			addComponent(new Label("To: "), 4, 3);
			addComponent(new TextField(), 5, 2);
			addComponent(new TextField(), 5, 3);
			setComponentAlignment(label, Alignment.MIDDLE_CENTER);
			addComponent(new Label("Button"), 0, 4);
			addComponent(new Label("Room-Name"), 1, 4);
			addComponent(new Label("Location"), 2, 4);
			addComponent(new Label("Size"), 3, 4);
			Set<Resource> resources = controller.getResources();
			int y = 5;
			int x = 1;
			for (Resource r : resources) {
				addComponent(new Label(r.getName()), x++, y);
				System.out.println(x);
				System.out.println(y);
				addComponent(new Label(r.getLocation()), x++, y);
				System.out.println(x);
				System.out.println(y);
				addComponent(new Label(Integer.toString(r.getSize())), x++, y);
				System.out.println(x);
				System.out.println(y);
				y++;
				x = 1;
			}
		}

	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		VerticalLayout layout = new VerticalLayout();

		name = new TextField();
		name.setCaption("Type your name here:");

		email = new TextField();
		email.setCaption("Type your email here:");

		pw = new TextField();
		pw.setCaption("Type your pw here:");

		Button login = new Button("Login");
		login.addClickListener(e -> {
			if ((controller.login(name.getValue(), pw.getValue()))
					|| (name.equals(name.getValue()) && pw.equals(pw.getValue()))) {
				layout.addComponent(new Label("Thanks " + name.getValue() + ", login worked!"));

				setContent(new Reserve());
			}
		});

		register = new Button("register now");
		register.addClickListener(e -> {
			try {
				controller.register(name.getValue(), email.getValue(), pw.getValue());
			} catch (Throwable e1) {

				e1.printStackTrace();
			}
		});

		layout.addComponents(name, email, pw, login, register);
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
	}

	public Button getButtonReg() {
		return register;
	}

	public TextField getTFN() {
		return name;
	}

	public TextField getTFE() {
		return email;
	}

	public TextField getTFP() {
		return pw;
	}

}
