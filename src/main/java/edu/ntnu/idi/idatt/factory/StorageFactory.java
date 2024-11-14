//package edu.ntnu.idi.idatt.factory;
//
//import edu.ntnu.idi.idatt.model.Storage;
//import edu.ntnu.idi.idatt.repository.StorageRepository;
//
//public class StorageFactory {
//  public Storage getStorage(String storageType, StorageRepository storageRepository) {
//	  return switch (storageType) {
//		  case "Fridge" -> storageRepository.getFridge();
//		  case "Cupboard" -> storageRepository.getCupboard();
//		  case "Other" -> storageRepository.getOtherStorage();
//		  default -> throw new IllegalArgumentException("Unknown storage type: " + storageType);
//	  };
//  }
//}
