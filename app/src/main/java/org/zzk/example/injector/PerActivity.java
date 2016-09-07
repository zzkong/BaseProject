package org.zzk.example.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zwl on 16/9/5.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
