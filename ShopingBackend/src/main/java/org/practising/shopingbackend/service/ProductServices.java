    package org.practising.shopingbackend.service;

    import jakarta.transaction.Transactional;
    import org.practising.shopingbackend.model.ProductsModel;
    import org.practising.shopingbackend.repository.AuthRepository;
    import org.practising.shopingbackend.repository.ProductsRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class ProductServices {

        private final ProductsRepository productsRepository;

        public ProductServices(ProductsRepository productsRepository) {
            this.productsRepository = productsRepository;
        }

        public ProductsModel save(ProductsModel productsModel) {
            return productsRepository.save(productsModel);
        }

        public List<ProductsModel> findAll() {
            return productsRepository.findAll();
        }
    }
