package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLDataSource implements IDataSource {
	private String user = "root";
	private String senha = "";
	private String url = "jdbc:mysql://localhost:3306/sistema_logistico";
	private String driverUrl = "com.mysql.cj.jdbc.Driver";
	private Connection sgbdConn = null;
	private Statement sqlInterpreter = null;

	@Override
	public ResultSet executarSelect(String sql) throws Exception {
		try {
			if(this.sqlInterpreter == null  ||  this.sqlInterpreter.isClosed())
				this.abrirConexao();
			ResultSet resultado = this.sqlInterpreter.executeQuery(sql);
			return resultado;
		} catch (Exception e) {
			Exception e2 = new Exception("Erro ao executar query!", e);
			throw e2;
		}
	}

	@Override
	public int executarQueryGeral(String sql) throws Exception {
		try {
			if(sqlInterpreter == null  ||  this.sqlInterpreter.isClosed())
				this.abrirConexao();
			int numeroTuplas = sqlInterpreter.executeUpdate(sql);
			return numeroTuplas;
		} catch (Exception e) {
			Exception e2 = new Exception("Erro ao executar query!", e);
			throw e2;
		}
	}

	@Override
	public void abrirConexao() throws Exception {
		try {
			Class.forName(this.driverUrl);
			this.fecharConexao();
			this.sgbdConn = DriverManager.getConnection(this.url, this.user, this.senha);
			this.sqlInterpreter = this.sgbdConn.createStatement();
		} catch (Exception e) {
			Exception e2 = new Exception("Erro ao abrir conexao!", e);
			throw e2;
		}
	}

	@Override
	public void fecharConexao() throws Exception{
		try {
			if (sqlInterpreter != null)
				sqlInterpreter.close();
			if (sgbdConn != null)
				sgbdConn.close();
		} catch (Exception e) {
			Exception e2 = new Exception("Erro ao fechar conexao!", e);
			throw e2;
		}
	}
}
