/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.view;

import java.time.LocalDateTime;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import ch.bfh.ti.soed.hs16.srs.green.controller.MyUIControllers;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
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

	private String r, l;
	private int s;
	private Button register;
	private TextField userName;
	private MyUIControllers controller;

	public MyUI() throws Throwable {
		controller = new MyUIControllers();
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	public class ReservationUI extends GridLayout {

		ReservationUI() {
			super(9, 6);
			createWindow();

		}

		@SuppressWarnings("deprecation")
		public void createWindow() {

			setMargin(true);
			setSpacing(true);

			Label label = new Label("Welcome " + userName.getValue());
			addComponent(label, 0, 0, 8, 0);

			InlineDateField date = new InlineDateField();
			date.setResolution(DateField.RESOLUTION_DAY);
			addComponent(date, 0, 1, 3, 4);

			addComponent(new Label("Time"), 4, 1, 8, 1);
			addComponent(new Label("From"), 4, 2);
			addComponent(new Label("Hrs:"), 5, 2);

			TextField fromHrs = new TextField();
			addComponent(fromHrs, 6, 2);

			addComponent(new Label("Min:"), 7, 2);
			TextField fromMin = new TextField();
			addComponent(fromMin, 8, 2);

			addComponent(new Label("To"), 4, 3);
			addComponent(new Label("Hrs:"), 5, 3);

			TextField toHrs = new TextField();
			addComponent(toHrs, 6, 3);

			addComponent(new Label("Min:"), 7, 3);
			TextField toMin = new TextField();
			addComponent(toMin, 8, 3);

			Button reservation = new Button("Make Reservation");
			addComponent(reservation, 4, 4);
			reservation.setStyleName(Reindeer.BUTTON_SMALL);

			Button getReservations = new Button("Get Reservations");
			addComponent(getReservations, 6, 4);
			getReservations.setStyleName(Reindeer.BUTTON_SMALL);

			Set<Resource> resources = controller.getResources();

			Table table = new Table("Please choose Room for Reservation");
			table.addContainerProperty("Roomname", String.class, null);
			table.addContainerProperty("Location", String.class, null);
			table.addContainerProperty("Size", Integer.class, null);

			int b = 1;
			for (Resource r : resources)
				table.addItem(new Object[] { r.getName(), r.getLocation(), r.getSize() }, b++);
			System.out.println("addcomponent");
			table.setSelectable(true);
			table.setImmediate(true);
			table.addListener(new ItemClickListener() {
				@Override
				public void itemClick(ItemClickEvent event) {
					@SuppressWarnings("rawtypes")
					Property roomNameP = event.getItem().getItemProperty("Roomname");
					@SuppressWarnings("rawtypes")
					Property locationP = event.getItem().getItemProperty("Location");
					@SuppressWarnings("rawtypes")
					Property sizeP = event.getItem().getItemProperty("Size");
					r = (String) roomNameP.getValue();
					l = (String) locationP.getValue();
					s = (int) sizeP.getValue();

				}
			});

			addComponent(table, 0, 5, 3, 5);

			reservation.addClickListener(ae -> {
				String dateS = date.getValue().toString();

				int day = Integer.parseInt(dateS.substring(8, 10));
				int year = Integer.parseInt(dateS.substring(24));

				try {
					controller.makeReservation(

							LocalDateTime.of(year, date.getValue().getMonth() + 1, day,
									Integer.parseInt(fromHrs.getValue()), Integer.parseInt(fromMin.getValue())),
							LocalDateTime.of(year, date.getValue().getMonth() + 1, day,
									Integer.parseInt(toHrs.getValue()), Integer.parseInt(toMin.getValue())),
							new Resource(r, s, l), controller.getCustomer(userName.getValue()));
				} catch (Throwable e) {

					e.printStackTrace();
				}

			});

			getReservations.addClickListener(ae -> {

				int b1 = 1;

				Table table1 = new Table("Reservations made by " + userName.getValue());

				table1.addContainerProperty("Roomname", String.class, null);
				table1.addContainerProperty("Location", String.class, null);
				table1.addContainerProperty("Starttime", String.class, null);
				table1.addContainerProperty("Endtime", String.class, null);
				table1.addContainerProperty("Size", Integer.class, null);

				Customer custom = controller.getCustomer(userName.getValue());

				Set<Reservation> reservations1 = null;
				try {
					reservations1 = controller.getReservationsMadeByCustomer(custom);
				} catch (Exception e) {

					e.printStackTrace();
				}

				for (Reservation r : reservations1)
					table1.addItem(
							new Object[] { r.getResource().getName(), r.getResource().getLocation(),
									r.getStartTime().toString(), r.getEndTime().toString(), r.getResource().getSize() },
							b1++);

				table1.setImmediate(true);
				addComponent(table1, 4, 5, 8, 5);
			});

		}

	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		VerticalLayout layout = new VerticalLayout();

		Panel panel = new Panel("Login");
		panel.setSizeUndefined();

		FormLayout content = new FormLayout();
		userName = new TextField("Username");
		content.addComponent(userName);

		PasswordField password = new PasswordField("Password");
		content.addComponent(password);

		Button login = new Button("Login");
		register = new Button("Register");

		login.setStyleName(Reindeer.BUTTON_SMALL);
		login.setWidth("86px");

		register.setStyleName(Reindeer.BUTTON_SMALL);
		register.setWidth("86px");

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.addComponent(login);
		hl.addComponent(register);

		content.addComponent(hl);
		content.setSizeUndefined();
		content.setMargin(true);

		panel.setContent(content);

		login.addClickListener(e -> {
			if ((controller.login(userName.getValue(), password.getValue()))
					|| (userName.equals(userName.getValue()) && password.equals(password.getValue()))) {

				setContent(new ReservationUI());
			}
		});

		register.addClickListener(e -> {
			try {
				controller.register(userName.getValue(), password.getValue());
			} catch (Throwable e1) {

				e1.printStackTrace();
			}
		});

		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(panel);
		setContent(layout);
	}

}
