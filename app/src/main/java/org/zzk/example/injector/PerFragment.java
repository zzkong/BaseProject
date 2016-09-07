package org.zzk.example.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by zwl on 16/9/5.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
