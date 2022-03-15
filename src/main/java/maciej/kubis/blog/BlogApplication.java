package maciej.kubis.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

//	@Autowired
//	private InitClass initClass;
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void applicationReady() {
//		initClass.initData();
//	}
}
