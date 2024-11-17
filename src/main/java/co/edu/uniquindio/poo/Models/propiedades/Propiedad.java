package co.edu.uniquindio.poo.Models.propiedades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Propiedad {

    private IntegerProperty id;
    private IntegerProperty idMotorType;    
    private StringProperty color;
    private StringProperty cilindraje;
    private IntegerProperty numDoors;
    private IntegerProperty numPassengers;
    private IntegerProperty numAirbags;
    private IntegerProperty numAxles;
    private IntegerProperty numEmergencyExits;
    private StringProperty horsePower;
    private StringProperty maximumSpeed;
    private IntegerProperty trunkCapacity;
    private StringProperty time100Km;
    private StringProperty loadCapacity; 
    private StringProperty typeTruck;
    private IntegerProperty airConditioning;
    private IntegerProperty reverseCamera;
    private IntegerProperty cruisingSpeed;
    private IntegerProperty absBrakes;
    private IntegerProperty airBrakes;
    private IntegerProperty collisionSensor;
    private IntegerProperty crossTrafficSensor;
    private IntegerProperty cuatroXcuatro;
    private IntegerProperty laneKeepingAssist;
    private IntegerProperty cambios;
    private IntegerProperty tipoTransmision;

    

    public Propiedad() {
        this.id = new SimpleIntegerProperty();
        this.idMotorType = new SimpleIntegerProperty();
        this.color = new SimpleStringProperty();
        this.cilindraje = new SimpleStringProperty();
        this.numDoors = new SimpleIntegerProperty(); 
        this.numPassengers = new SimpleIntegerProperty();
        this.numAirbags = new SimpleIntegerProperty();
        this.numAxles = new SimpleIntegerProperty();
        this.numEmergencyExits = new SimpleIntegerProperty();
        this.horsePower = new SimpleStringProperty();
        this.maximumSpeed = new SimpleStringProperty();
        this.trunkCapacity = new SimpleIntegerProperty();
        this.time100Km = new SimpleStringProperty();
        this.loadCapacity = new SimpleStringProperty();
        this.typeTruck = new SimpleStringProperty();
        this.airConditioning = new SimpleIntegerProperty();
        this.reverseCamera = new SimpleIntegerProperty();
        this.cruisingSpeed = new SimpleIntegerProperty();
        this.absBrakes = new SimpleIntegerProperty();
        this.airBrakes = new SimpleIntegerProperty();
        this.collisionSensor = new SimpleIntegerProperty();
        this.crossTrafficSensor = new SimpleIntegerProperty();
        this.cuatroXcuatro = new SimpleIntegerProperty();
        this.laneKeepingAssist = new SimpleIntegerProperty();
    }

    // Getters y Setters para las propiedades
    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }
    
    public IntegerProperty idMotorTypeProperty() {
        return idMotorType;
    }

    public void setIdMotorType(int idMotorType) {
        this.idMotorType.set(idMotorType);
    }

    public int getIdMotorType() {
        return idMotorType.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getColor() {
        return color.get();
    }
    
    public StringProperty cilindrajeProperty() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje.set(cilindraje);
    }

    public String getCilindraje() {
        return cilindraje.get();
    }

    public IntegerProperty numDoorsProperty() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors.set(numDoors);
    }

    public int getNumDoors() {
        return numDoors.get();
    }

    public IntegerProperty numPassengersProperty() {
        return numPassengers;
    }

    public void setnumPassengers(int numPassengers) {
        this.numPassengers.set(numPassengers);
    }

    public int getnumPassengers() {
        return numPassengers.get();
    }

    public IntegerProperty numAirbagsProperty() {
        return numAirbags;
    }

    public void setNumAirbags(int numAirbags) {
        this.id.set(numAirbags);
    }

    public int getNumAirbags() {
        return numAirbags.get();
    }

    public IntegerProperty numAxlesProperty() {
        return numAxles;
    }

    public void setNumAxles(int numAxles) {
        this.numAxles.set(numAxles);
    }

    public int getNumAxles() {
        return numAxles.get();
    }

    public IntegerProperty numEmergencyExitsProperty() {
        return numAirbags;
    }

    public void setNumEmergencyExits(int numEmergencyExits) {
        this.id.set(numEmergencyExits);
    }

    public int getNumEmergencyExits() {
        return numEmergencyExits.get();
    }

    public StringProperty horsePowerProperty() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower.set(horsePower);
    }

    public String getHorsePower() {
        return horsePower.get();
    }

    public StringProperty maximumSpeedProperty() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(String maximumSpeed) {
        this.maximumSpeed.set(maximumSpeed);
    }

    public String getMaximumSpeed() {
        return maximumSpeed.get();
    }

    public IntegerProperty trunkCapacityProperty() {
        return trunkCapacity;
    }

    public void setTrunkCapacity(int trunkCapacity) {
        this.trunkCapacity.set(trunkCapacity);
    }

    public int getTrunkCapacity() {
        return trunkCapacity.get();
    }

    public StringProperty time100KmProperty() {
        return time100Km;
    }

    public void setTime100Km(String time100Km) {
        this.time100Km.set(time100Km);
    }

    public String getTime100Km() {
        return time100Km.get();
    }

    public StringProperty loadCapacityProperty() {
        return loadCapacity;
    }

    public void setLoadCapacity(String loadCapacity) {
        this.loadCapacity.set(loadCapacity);
    }

    public String getLoadCapacity() {
        return loadCapacity.get();
    }

    public StringProperty typeTruckProperty() {
        return typeTruck;
    }

    public void setTypeTruck(String typeTruck) {
        this.typeTruck.set(typeTruck);
    }

    public String getTypeTruck() {
        return typeTruck.get();
    }

    public IntegerProperty airConditioningProperty() {
        return airConditioning;
    }

    public void setAirConditioning(int airConditioning) {
        this.id.set(airConditioning);
    }

    public int getAirConditioning() {
        return airConditioning.get();
    }

    public IntegerProperty reverseCameraProperty() {
        return reverseCamera;
    }

    public void setReverseCamera(int reverseCamera) {
        this.reverseCamera.set(reverseCamera);
    }

    public int getReverseCamera() {
        return reverseCamera.get();
    }

    public IntegerProperty cruisingSpeedProperty() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(int cruisingSpeed) {
        this.cruisingSpeed.set(cruisingSpeed);
    }

    public int getCruisingSpeed() {
        return cruisingSpeed.get();
    }

    public IntegerProperty absBrakesProperty() {
        return absBrakes;
    }

    public void setAbsBrakes(int absBrakes) {
        this.absBrakes.set(absBrakes);
    }

    public int getAbsBrakes() {
        return absBrakes.get();
    }

    public IntegerProperty airBrakesProperty() {
        return airBrakes;
    }

    public void setAirBrakes(int airBrakes) {
        this.airBrakes.set(airBrakes);
    }

    public int getAirBrakes() {
        return airBrakes.get();
    }

    public IntegerProperty collisionSensorProperty() {
        return collisionSensor;
    }

    public void setCollisionSensor(int collisionSensor) {
        this.collisionSensor.set(collisionSensor);
    }

    public int getCollisionSensor() {
        return collisionSensor.get();
    }

    public IntegerProperty crossTrafficSensorProperty() {
        return crossTrafficSensor;
    }

    public void setCrossTrafficSensor(int crossTrafficSensor) {
        this.crossTrafficSensor.set(crossTrafficSensor);
    }

    public int getCrossTrafficSensor() {
        return crossTrafficSensor.get();
    }

    public IntegerProperty cuatroXcuatroProperty() {
        return cuatroXcuatro;
    }

    public void setCuatroXcuatro(int cuatroXcuatro) {
        this.cuatroXcuatro.set(cuatroXcuatro);
    }

    public int getCuatroXcuatro() {
        return cuatroXcuatro.get();
    }

    public IntegerProperty laneKeepingAssistProperty() {
        return laneKeepingAssist;
    }

    public void setLaneKeepingAssist(int laneKeepingAssist) {
        this.laneKeepingAssist.set(laneKeepingAssist);
    }

    public int getLaneKeepingAssist() {
        return laneKeepingAssist.get();
    }

    public IntegerProperty cambiosProperty() {
        return cambios;
    }

    public void setCambios(int cambios) {
        this.cambios.set(cambios);
    }

    public int getCambios() {
        return cambios.get();
    }

    public IntegerProperty tipoTransmision() {
        return tipoTransmision;
    }

    public void setTipoTransmision(int tipoTransmision) {
        this.tipoTransmision.set(tipoTransmision);
    }

    public int getTipoTransmision() {
        return tipoTransmision.get();
    }
}
