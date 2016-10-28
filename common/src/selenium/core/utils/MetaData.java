package selenium.core.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
	public @interface MetaData{
		String name();
		String altId();
		int level() default -1;
		boolean skip() default false;
		
	}
