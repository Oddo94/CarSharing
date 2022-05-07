package carsharing.model.dao;

import carsharing.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements Dao<Car> {
    private String getCompanyCarListStatement = "SELECT CR.ID, CR.NAME FROM COMPANY CMP" +
            "INNER JOIN CAR CR ON CMP.ID = CR.ID" +
            "WHERE CMP.NAME = ?";

    private String createCarStatement = "INSERT INTO CAR(NAME, COMPANY_ID) VALUES(?, (SELECT ID FROM COMPANY WHERE NAME = ?))";
    private Connection databaseConnection;
    private String companyName;

    public CarDao(Connection databaseConnection, String companyName) {
        this.databaseConnection = databaseConnection;
        this.companyName = companyName;
    }

    @Override
    public Car get(long id) {
        return null;
    }

    @Override
    public List<Car> getAll() {
        List<Car> carsList = new ArrayList<>();

        try(PreparedStatement preparedStatement = databaseConnection.prepareStatement(getCompanyCarListStatement)) {
            preparedStatement.setString(1, companyName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String carName = resultSet.getString(2);

                Car car = new Car(id, carName);
                carsList.add(car);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public int save(Car car) {
        if(car == null) {
            return -1;
        }

        try(PreparedStatement preparedStatement = databaseConnection.prepareStatement(createCarStatement)) {
            preparedStatement.setString(1, car.getName());
            preparedStatement.setString(2, companyName);
            preparedStatement.execute();

            return 0;

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(long id, Car car) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
