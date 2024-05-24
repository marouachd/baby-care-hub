package co.simplon.babycarehub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = { "classpath:db/schema-tests.ddl.sql",
	"classpath:db/data-tests.dml.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
class BaseMvcTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldBeTrue() {
	Assertions.assertTrue(false);
    }

}
