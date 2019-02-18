package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Estoque;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;

import com.springcommerceapi.SpringCommerceAPI.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> relatorioDeEntrada(String dataInicio, String dataFinal) throws ParseException {
        Date dataI = null;
        Date dataF = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dataTextoAtual = new String(dataInicio);
        String dataTextoAntiga = new String(dataFinal);
        format.setLenient(false);
        dataI = format.parse(dataTextoAtual);
        dataF = format.parse(dataTextoAntiga);

        List<Estoque> relatorioEntrada = new ArrayList<>();
        List<Estoque> estoques = findAll();

        for(Estoque x: estoques){
            Date escopo = null;

            String umaDta = new String(x.getData().toString());
            escopo = format.parse(umaDta);

            if ((escopo.compareTo(dataI) == 1 || escopo.compareTo(dataI) == 0) && (escopo.compareTo(dataF) == 0 ||
                    escopo.compareTo(dataF) == -1) && (x.getTipo() == 1)){
                relatorioEntrada.add(x);
            }
        }
        return relatorioEntrada;

    }


    public List<Estoque> findAll() {
        return (List<Estoque>) this.estoqueRepository.findAll();
    }


    public List<Estoque> relatorioDeSaida(String dataInicio, String dataFinal) throws ParseException {
        Date dataI = null;
        Date dataF = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dataTextoAtual = new String(dataInicio);
        String dataTextoAntiga = new String(dataFinal);
        format.setLenient(false);
        dataI = format.parse(dataTextoAtual);
        dataF = format.parse(dataTextoAntiga);

        List<Estoque> relatorioSaida = new ArrayList<>();
        List<Estoque> estoques = findAll();

        for(Estoque x: estoques){
            Date escopo = null;

            String umaDta = new String(x.getData().toString());
            escopo = format.parse(umaDta);

            if ((escopo.compareTo(dataI) == 1 || escopo.compareTo(dataI) == 0) && (escopo.compareTo(dataF) == 0 ||
                    escopo.compareTo(dataF) == -1) && (x.getTipo() == 2)){
                relatorioSaida.add(x);
            }

        }

        return relatorioSaida;

    }

}