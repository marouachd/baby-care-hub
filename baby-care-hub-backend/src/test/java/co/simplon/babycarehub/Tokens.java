package co.simplon.babycarehub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Tokens {

    @Value("${babycarehub.tests.fake-token}")
    private String fake;

    @Value("${babycarehub.tests.bad-secret-token}")
    private String badSecret;

    @Value("${babycarehub.tests.bad-issuer-token}")
    private String badIssuer;

    @Value("${babycarehub.tests.expired-token}")
    private String expired;

    String get(final String name) {
	switch (name) {
	case "fake":
	    return fake;
	case "badSecret":
	    return badSecret;
	case "badIssuer":
	    return badIssuer;
	case "expired":
	    return expired;

	default:
	    throw new IllegalArgumentException(
		    "Unexpected value: " + name);
	}
    }

}
