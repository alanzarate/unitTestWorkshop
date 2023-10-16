import org.example.Elevator;
import org.junit.Rule;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ElevatorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void siComienzaAscensorPizo1PuertaCerrada() throws Exception{
        Elevator e = new Elevator(3);
        assertEquals(1 , e.getCurrentFloor());
        assertEquals(false, e.getDoorOpen());

    }

    @Test
    public void noCrearPersonaYObtenerLaSolicitudParaMoverse() throws Exception{
        Elevator e = new Elevator(3);
        exception.expect(Exception.class);
        e.getSolicitudPendiente();

    }

    @Test
    public void crearPersonaYObtenerLaSolicitudParaMoverCuandoEstaEnelPrimerPiso() throws Exception{
        Elevator e = new Elevator(3);
        e.createPersona(); // 1
        e.setCurrentDestiny(e.getSolicitudPendiente()); // cuando se pulsa el boton desde el piso
        assertEquals(1, e.getSolicitudPendiente()); // por que se realizo de el piso 1

        e.moveOneStepToDestiny(); // se mueve un paso si es necesario
        assertTrue(e.isInRequest()); // se pregunta si esta en el piso solicitado
    }

    @Test
    public void siAscensorIntentaMoverseConPuertaAbiertaLanzarException() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona();
        e.presionaBotonDePiso(e.getSolicitudPendiente());

        // lanzar una excepcion si se mueve con la puerta abierta
        exception.expect(Exception.class);
        e.moveOneStepToDestiny();

    }
    @Test
    public void siDestinoFueraDeRangoLanzarUnaException() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona();
        e.setCurrentDestiny(e.getSolicitudPendiente());
        while(!e.isInRequest()){
            e.moveOneStepToDestiny();
            assertFalse(e.getDoorOpen()); // testear la puerta
        }
        e.setDoorOpen(true);
       // lanzar una excepcion fuera de rango
        exception.expect(Exception.class);
        e.setCurrentDestiny(4);

        exception.expect(Exception.class);
        e.setCurrentDestiny(-1);


    }

    @Test
    public void moverALaPersona() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona();
        assertEquals(-1, e.getCurrentPerson());
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        e.presionaBotonDeAscensor(0);
        assertTrue(e.getDoorOpen());
        assertEquals(0, e.getCurrentFloor());
        assertNotSame(-1, e.getCurrentPerson());

    }

    @Test
    public void siPersonaIngresaDestinyIgualAlCurrentPisoLanzarException() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona();
        assertEquals(-1, e.getCurrentPerson());
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        exception.expect(Exception.class);
        e.presionaBotonDeAscensor(1);
        assertTrue(e.getDoorOpen());
        assertEquals(0, e.getCurrentFloor());
        assertNotSame(-1, e.getCurrentPerson());

    }


    @Test
    public void siLaSegundaPersonaSolicitaSuMismoPisoComoDestinoLanzarException() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona(); // 1
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        e.presionaBotonDeAscensor(0);
        e.personaSalio();

        e.createPersona(); // 2
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        exception.expect(Exception.class);
        e.presionaBotonDeAscensor(2);


    }

    @Test
    public void siPersonaNoSalioLanzarException() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona(); // 1
        e.presionaBotonDePiso(e.getSolicitudPendiente()); // la persona presiona el boton de su piso
        e.presionaBotonDeAscensor(0); // la personaPresiona el boton de afuera
        //e.personaSalio();

        e.createPersona(); // 2
        exception.expect(Exception.class);
        e.presionaBotonDePiso(e.getSolicitudPendiente());


    }





    @Test
    public void elAscensorLlegoDondeLaPersonaEnLaSegundaLlamada() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona(); // 1
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        e.presionaBotonDeAscensor(0);
        e.personaSalio();

        e.createPersona(); // 2
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        assertEquals(2, e.getCurrentFloor());


    }




    @Test
    public void mover3Personas() throws Exception{
        Elevator  e = new Elevator(3);
        e.createPersona(); // 1
        assertEquals(-1, e.getCurrentPerson());
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        assertEquals(1, e.getCurrentFloor());
        e.presionaBotonDeAscensor(0);
        assertTrue(e.getDoorOpen());
        assertEquals(0, e.getCurrentFloor());
        assertNotSame(-1, e.getCurrentPerson());
        e.personaSalio();
        assertEquals(-1, e.getCurrentPerson());

        e.createPersona(); // 2
        assertEquals(-1, e.getCurrentPerson());
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        assertEquals(2, e.getCurrentFloor());
        e.presionaBotonDeAscensor(0);
        assertTrue(e.getDoorOpen());
        assertEquals(0, e.getCurrentFloor());
        assertNotSame(-1, e.getCurrentPerson());
        e.personaSalio();
        assertEquals(-1, e.getCurrentPerson());

        e.createPersona(); // 3
        assertEquals(-1, e.getCurrentPerson());
        e.presionaBotonDePiso(e.getSolicitudPendiente());
        assertEquals(3, e.getCurrentFloor());
        e.presionaBotonDeAscensor(0);
        assertTrue(e.getDoorOpen());
        assertEquals(0, e.getCurrentFloor());
        assertNotSame(-1, e.getCurrentPerson());
        e.personaSalio();
        assertEquals(-1, e.getCurrentPerson());


    }




}
