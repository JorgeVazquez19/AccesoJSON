package com.company;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface DataManager {

    public void metaDatosResultSet() throws IOException;
    public void ficheroAbD(File file) throws SQLException, IOException;
    public void anadir() throws IOException;
    public void leer();


}
