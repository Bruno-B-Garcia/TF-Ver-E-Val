package com.csvop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.csvop.casosDeUso.Politicas.CalculoCustoViagem;
import com.csvop.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.csvop.casosDeUso.Politicas.CustoViagem;

import org.junit.jupiter.api.Test;

public class CustoViagemTest {
    @Test
    public void testaCustoViagem(){
        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(35.0);

        CustoViagem cv = new CustoViagem(ccv);
        double custoObs = cv.custoViagem(null, null);
        double custoEsp = 35;
        assertEquals(custoEsp,custoObs,0.001);
    }
    
}
