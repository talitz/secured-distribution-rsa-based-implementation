import java.math.BigInteger;
public class KEY {
	
	private BigInteger n, e;
	
	public KEY(BigInteger e, BigInteger n) {
		this.e = e;
		this.n = n;
	}
	public BigInteger getE() {
		return this.e;
	}
	public BigInteger getN() {
		return this.n;
	}
}
