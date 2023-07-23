package com.drogeria.backend.service;



import com.drogeria.backend.dto.SaleDTO;

import java.io.IOException;
import java.util.List;

public interface SaleService {

     SaleDTO saveVenta(SaleDTO saleDTO) throws IOException;

     List<SaleDTO> getVentas();

}
