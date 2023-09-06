package it.zuccante.stage;

import it.zuccante.stage.AmbulanzaVeloceApp;
import it.zuccante.stage.config.AsyncSyncConfiguration;
import it.zuccante.stage.config.EmbeddedNeo4j;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { AmbulanzaVeloceApp.class, AsyncSyncConfiguration.class })
@EmbeddedNeo4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface IntegrationTest {
}
