package org.launchcode.givewise.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.launchcode.givewise.models.Favorites;
import org.launchcode.givewise.models.Product;
import org.launchcode.givewise.models.data.FavoritesRepository;
import org.launchcode.givewise.request.FavoriteRequest;
import org.launchcode.givewise.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.launchcode.givewise.config.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@Slf4j
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping("/add")
    public ResponseEntity<Favorites> addFavourite(@RequestBody FavoriteRequest favoriteDto){
        Favorites favorites = favoritesService.addFavorite(favoriteDto);
        log.info("Favorites added: {}", favorites);
        return ResponseEntity.ok(favorites);
    }


//    @GetMapping("/display/{userId}")
//    public ResponseEntity<List<Favorites>> getFavoritesByUserId(@PathVariable Integer userId){
//        try{
//            List<Favorites> favorites = favoritesService.findFavoritesWithProductDetails(userId);
//            return new ResponseEntity<>(favorites, HttpStatus.OK);
//        } catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @GetMapping("/display/{userId}")
    public ResponseEntity<List<Product>> getFavoritesByUserId(@PathVariable Integer userId){
        try{
            List<Product> favorites = favoritesService.userFavProduct(userId);
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteFavorite(@PathVariable Integer id){
//        try{
//            favoritesService.deleteFavorite(id);
//            log.info("Favorite deleted with id: {}", id);
//            return ResponseEntity.noContent().build();
//        }catch (Exception e) {
//            log.error("Error deleting Favorite with id: {}", id, e);
//            return ResponseEntity.status(500).build();
//        }
//    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteFavorite( @PathVariable Integer productId) {
        try {
            favoritesService.deleteFavorite(productId);
            log.info("Favorite deleted with productId: {}", productId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting Favorite with productId: {}", productId, e);
            return ResponseEntity.status(500).build();
        }
    }


}
