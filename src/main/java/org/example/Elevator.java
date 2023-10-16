package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Elevator {
    private Integer totalFloors;
    private Queue<Integer> queue;
    private Random random;
    private Boolean isDoorOpen;
    private Integer currentFloor;
    private Integer currentPerson;
    private Integer currentDestiny;
    public Elevator(int totalFloors){
        this.totalFloors = totalFloors;
        this.queue = new LinkedList<>();
        this.random = new Random(12345);
        this.isDoorOpen = false;
        this.currentFloor = 1;
        this.currentPerson = -1;
        this.currentDestiny = -1;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Boolean getDoorOpen() {
        return isDoorOpen;
    }


    public void createPersona(){
        int valorFloor = random.nextInt(totalFloors+1);
        queue.add(valorFloor);

    }

    public boolean checkIfSomeoneIsWaiting(){
        return queue.size() != 0;
    }

    public int getSolicitudPendiente() throws Exception{
        if(queue.size() > 0){
            return queue.peek();
        }
        throw new Exception("No existe nadie por que hacer");

    }
    public void moveOneStepToDestiny() throws  Exception{
        if(isDoorOpen){
            throw new Exception("Open Door");
        }
        if(currentDestiny > currentFloor){
            currentFloor  += 1;
        }
        if(currentDestiny < currentFloor){
            currentFloor += -1;
        }

    }
    public void setCurrentDestiny(int destiny) throws Exception {
        if(destiny > totalFloors || destiny <0){
            throw new Exception("Invalid destiny");
        }
        currentDestiny = destiny;
    }
    public boolean isInRequest() throws Exception{
        if(currentFloor.equals(currentDestiny)){
            currentDestiny = -1;
            return true;
        }
        return false;
    }
    public void takeSolicitud() throws Exception{
        currentPerson =  queue.remove();

    }

    public void setDoorOpen(Boolean doorOpen) {
        isDoorOpen = doorOpen;
    }


    public void presionaBotonDePiso(int solicitud) throws Exception {
        if(currentPerson!= -1){
            throw new Exception("Ocupado");
        }
        moverTo(solicitud);

    }

    public void setCurrentPerson(Integer currentPerson) {
        this.currentPerson = currentPerson;
    }

    public void presionaBotonDeAscensor(int solicitud) throws Exception {
        if(solicitud == currentFloor){
            throw new Exception("Same floor");
        }
        takeSolicitud();
        moverTo(solicitud);
    }
    public void personaSalio(){
        this.currentPerson = -1;
    }
    private void moverTo(int destiny) throws Exception{
        setDoorOpen(false);
        setCurrentDestiny(destiny);
        while (!isInRequest()){
            moveOneStepToDestiny();
        }
        setDoorOpen(true);
    }

    public int getCurrentPerson() {
        return currentPerson;
    }

    public int getCurrentDestiny() {
        return currentDestiny;
    }
}
