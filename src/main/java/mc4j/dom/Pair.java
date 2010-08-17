package mc4j.dom;

import java.io.Serializable;

public class Pair<T1,T2> implements Serializable {
	public T1 t1;
	public T2 t2;
	
	public Pair() {
		t1 = null;
		t2 = null;
	}

	public Pair(T1 t1, T2 t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
}