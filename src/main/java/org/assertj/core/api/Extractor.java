package org.assertj.core.api;

public interface Extractor<T, R> {

    R apply(T input);
}
