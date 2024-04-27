package org.menekseyuncu.storemanagementsystem.common;

import java.util.List;

public interface BaseMapper<S, T> {

    T map(S source);

    List<T> map(List<S> source);
}