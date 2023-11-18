package developer.jorayev.ELasticLog;

import org.apache.http.HttpRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;


public class MyClass {

//    public static void main(String[] args) {
//        // MyClass nomidagi class uchun modulni aniqlash
//        Module module = MyClass.class.getModule();
//        String moduleName = module.getName();
//        System.out.println("MyClass class moduli: " + moduleName);
//    }

//    public static void main(String[] args) {
//        // Rasmlarni olish
//        List<String> imageUrls = getImageUrls();
//
//        // Stream orqali rasmlarni ushlab olish
//        List<byte[]> images = imageUrls.stream()
//                .map(MyClass::loadImageFromUrl)
//                .collect(Collectors.toList());
//
//        // Ushlab olgan rasmlarni ishlatish
//        for (byte[] image : images) {
//            // Rasmlarni ishlatishingiz kerak bo'lgan logika
//            // Misol uchun, konsolga chiqaring
//            System.out.println("Loaded image: " + image.length + " bytes");
//        }
//    }
//
//    // Mock API dan rasmlarni olish
//    private static List<String> getImageUrls() {
//        return List.of("url1", "url2", "url3"); // O'zgartirish mumkin
//    }
//
//    // HttpClient orqali rasmi URL dan yuklash
//    private static byte[] loadImageFromUrl(String imageUrl) {
//        try {
//            HttpClient httpClient = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(imageUrl))
//                    .build();
//
//            HttpResponse<byte[]> response = httpClient.send((java.net.http.HttpRequest) request, HttpResponse.BodyHandlers.ofByteArray());
//
//            if (response.statusCode() == 200) {
//                return response.body();
//            } else {
//                System.out.println("Failed to load image from URL: " + imageUrl + ", Status code: " + response.statusCode());
//            }
//        } catch (Exception e) {
//            System.out.println("Exception while loading image from URL: " + imageUrl);
//            e.printStackTrace();
//        }
//
//        return new byte[0];
//    }

}
