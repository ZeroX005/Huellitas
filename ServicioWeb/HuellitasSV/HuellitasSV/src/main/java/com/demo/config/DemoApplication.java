package com.demo.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.demo.controller.BackupController;
import com.demo.controller.DashboardReclamoController;
import com.demo.controller.DashboardReservaController;
import com.demo.controller.DashboardUsuarioController;
import com.demo.controller.PaseoController;
import com.demo.controller.ReclamoController;
import com.demo.controller.TarjetaController;
import com.demo.controller.UsuarioController;

@ApplicationPath("rest")
public class DemoApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(UsuarioController.class);
		classes.add(PaseoController.class);
		classes.add(ReclamoController.class);
		classes.add(TarjetaController.class);
		classes.add(DashboardUsuarioController.class);
		classes.add(DashboardReclamoController.class);
		classes.add(DashboardReservaController.class);
		classes.add(BackupController.class);
		return classes;
	}
	
	
}
