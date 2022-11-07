package io.fouad.examples.systemdnotify;

import io.fouad.systemd.SystemDNotifier;
import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class AppLifecycle {
	
	void onStartUp(@Observes StartupEvent event) {
		try {
			SystemDNotifier.notify("STATUS=Quarkus is starting...");
			
			// some startup logic
			
			SystemDNotifier.notify("READY=1");
			SystemDNotifier.notify("STATUS=Quarkus is up and running");
		} catch (Throwable e) {
			Log.fatal("Failed to initialize the server!", e);
			SystemDNotifier.notify(String.format("BUSERROR=%s".formatted(e.getMessage())));
			throw e;
		}
	}
	
	void onShutdown(@Observes ShutdownEvent event) {
		SystemDNotifier.notify("STOPPING=1");
		SystemDNotifier.notify("STATUS=Quarkus is stopping...");
		
		// some shutdown logic
		
		SystemDNotifier.notify("STATUS=Quarkus is stopped");
	}
}