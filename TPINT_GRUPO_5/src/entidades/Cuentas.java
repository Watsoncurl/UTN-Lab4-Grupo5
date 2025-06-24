package entidades;

public class Cuentas {

	private int id_cuenta;
	private int id_cliente;
	private tipo_cuenta id_tipo_cuenta;
	private String nro_cuenta;
	private String cbu;
	private float saldo;
	private boolean estado;
	
	
	public Cuentas() {}
	
	public Cuentas(int id_cuenta, int id_cliente, tipo_cuenta id_tipo_cuenta, String nro_cuenta, String cbu, float saldo, boolean estado) {
		super();
		this.id_cuenta = id_cuenta;
		this.id_cliente = id_cliente;
		this.id_tipo_cuenta = id_tipo_cuenta;
		this.nro_cuenta = nro_cuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}

	
	public int getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_tipo_cuenta() {
		return id_tipo_cuenta.getId_tipo_cuenta();
	}

	public void setId_tipo_cuenta(int id_tipo_cuenta) {
		this.id_tipo_cuenta.setId_tipo_cuenta(id_tipo_cuenta);
	}

	public String getNro_cuenta() {
		return nro_cuenta;
	}

	public void setNro_cuenta(String nro_cuenta) {
		this.nro_cuenta = nro_cuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
