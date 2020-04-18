package emsi.jebji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import emsi.jebji.dao.ProduitRepository;
import emsi.jebji.entities.Produit;

@SpringBootApplication
public class MyCatalogueApplication implements CommandLineRunner {

	@Autowired
	private ProduitRepository produitRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------------------");
		for(int i=0; i<10; i++)
		{
			produitRepository.save(new Produit(null,"PC",9500,5));
			produitRepository.save(new Produit(null,"Imprimante",2500,15));
			produitRepository.save(new Produit(null,"Router",500,55));
		}
		
		
		Page<Produit> produits = produitRepository.findAll(PageRequest.of(0, 5));
		System.out.println("Size : " + produits.getSize());
		System.out.println("Pages : " + produits.getTotalPages());
		System.out.println("Elements : " + produits.getTotalElements());
		
		System.out.println("------------------------");
		produits.getContent().forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("------------------------");
		Page<Produit> produits2 = produitRepository.findByDesignationContains("P", PageRequest.of(0, 5));
		produits2.getContent().forEach(p->{
		System.out.println(p.toString());
		});
	}

}
