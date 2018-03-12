package sharkodlak.tree;

public interface DataWrapper<T> {
	/** Returns instance's boxed data.
	 */
	public T getData();

	/** Set new data.
	 */
	public DataWrapper<T> setData(T data);
}
