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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.ti.soed.hs16.srs.green.controller.MyUIControllers;
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
	private TextField userName, preName, lastName;

	private TextField email;
	private TextField pw;
	private MyUIControllers controller = new MyUIControllers();

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	public class Reserve extends GridLayout {

		@SuppressWarnings("deprecation")
		Reserve() {
			super(6, controller.getAmountRooms() + 5);
			System.out.println(controller.getAmountRooms());
			Label label = new Label(userName.getValue());
			addComponent(label, 0, 0, 5, 0);
			InlineDateField date = new InlineDateField();
			date.setResolution(DateField.RESOLUTION_DAY);

			TextField from = new TextField();
			TextField to = new TextField();

			addComponent(date, 0, 1, 3, 3);
			addComponent(new Label("Time: "), 4, 1, 5, 1);
			addComponent(new Label("From: "), 4, 2);
			addComponent(new Label("To: "), 4, 3);
			addComponent(from, 5, 2);
			addComponent(to, 5, 3);
			setComponentAlignment(label, Alignment.MIDDLE_CENTER);
			Set<Resource> resources = controller.getResources();

			int b = 1;

			Table table = new Table("Please choose Room for Reservation");

			table.addContainerProperty("Roomname", String.class, null);
			table.addContainerProperty("Location", String.class, null);
			table.addContainerProperty("Size", Integer.class, null);

			for (Resource r : resources)
				table.addItem(new Object[] { r.getName(), r.getLocation(), r.getSize() }, b++);
			System.out.println("addcomponent");
			table.setSelectable(true);
			table.setImmediate(true);
			table.addListener(new ItemClickListener() {
				@Override
				public void itemClick(ItemClickEvent event) {
					Property roomNameP = event.getItem().getItemProperty("Roomname");
					Property locationP = event.getItem().getItemProperty("Location");
					Property sizeP = event.getItem().getItemProperty("Size");
					r = (String) roomNameP.getValue();
					l = (String) locationP.getValue();
					s = (int) sizeP.getValue();

				}
			});

			addComponent(table, 0, 5);

			Button reservation = new Button("make reservation");
			addComponent(reservation, 4, 4);
			reservation.addClickListener(ae -> {
				String dateS = date.getValue().toString();

				int day = Integer.parseInt(dateS.substring(8, 10));
				int year = Integer.parseInt(dateS.substring(24));

				// int month = Integer.parseInt(dateS.substring(4, 7));
				try {
					controller.makeReservation(

							LocalDateTime.of(year, date.getValue().getMonth() + 1, day,
									Integer.parseInt(from.getValue()), 0),
							LocalDateTime.of(year, date.getValue().getMonth() + 1, day, Integer.parseInt(to.getValue()),
									0),
							new Resource(r, s, l), controller.getCustomer(userName.getValue()));
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});

			Button getReservations = new Button("getReservations");
			getReservations.addClickListener(ae -> {
				Set<Reservation> reservations = controller
						.getReservationsMadeByCustomer(controller.getCustomer(userName.getValue()));
				for (Reservation r : reservations)
					System.out.println(r);
			});
			addComponent(getReservations,5,4);
		}

	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		VerticalLayout layout = new VerticalLayout();

		userName = new TextField();
		userName.setCaption("Type your username here:");

		preName = new TextField();
		preName.setCaption("Type your prename here:");

		lastName = new TextField();
		lastName.setCaption("Type your lastname here:");

		email = new TextField();
		email.setCaption("Type your email here:");

		pw = new TextField();
		pw.setCaption("Type your pw here:");

		Button login = new Button("Login");
		login.addClickListener(e -> {
			if ((controller.login(userName.getValue(), pw.getValue()))
					|| (userName.equals(userName.getValue()) && pw.equals(pw.getValue()))) {
				System.out.println("great");
				layout.addComponent(new Label("Thanks " + userName.getValue() + ", login worked!"));

				setContent(new Reserve());
			}
		});

		register = new Button("register now");
		register.addClickListener(e -> {
			try {
				controller.register(userName.getValue(), preName.getValue(), lastName.getValue(), email.getValue(),
						pw.getValue());
			} catch (Throwable e1) {

				e1.printStackTrace();
			}
		});

		layout.addComponents(userName, email, pw, login, register);
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
	}

	public Button getButtonReg() {
		return register;
	}

	public TextField getTFN() {
		return userName;
	}

	public TextField getTFE() {
		return email;
	}

	public TextField getTFP() {
		return pw;
	}

	public TextField getUserName() {
		return userName;
	}

	public void setUserName(TextField userName) {
		this.userName = userName;
	}

}
