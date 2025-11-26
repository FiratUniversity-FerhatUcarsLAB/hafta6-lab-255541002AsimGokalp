package sinemabiletifiyatlandirma;

import java.util.Scanner;
public class Proje2_SinemaBileti {
	
	
            public static void main(String[] args) {
            	/**
        		* Ad Soyad: ASIM GÖKALP
        		* Numara: 255541002
        		* Proje: Sinema Bileti Fiyatlandırma
        		* Tarih: 26.11.2025
        		*/
        		

	        Scanner input = new Scanner(System.in);

	        System.out.print("Gun (1=Pzt, 2=Sal, 3=Car, 4=Per, 5=Cum, 6=Cmt, 7=Paz): ");
	        int gun = input.nextInt();

	        System.out.print("Saat (8-23): ");
	        int saat = input.nextInt();

	        System.out.print("Yas: ");
	        int yas = input.nextInt();

	        System.out.print("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diger): ");
	        int meslek = input.nextInt();

	        System.out.print("Film Turu (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
	        int filmTuru = input.nextInt();

	        // Hesaplamalar
	        double basePrice = calculateBasePrice(gun, saat);
	        double discountRate = calculateDiscount(yas, meslek, gun);   // 0.20 gibi
	        double formatExtra = getFormatExtra(filmTuru);
	        double finalPrice = calculateFinalPrice(basePrice, discountRate, formatExtra);

	        String ticketInfo = generateTicketInfo(
	                gun, saat, yas, meslek, filmTuru,
	                basePrice, discountRate, formatExtra, finalPrice);
	        

	        System.out.println();
	        System.out.println(ticketInfo);

	        input.close();
	    }

	    // 1) Hafta sonu mu?
	    public static boolean isWeekend(int gun) {
	        return (gun == 6) || (gun == 7);  // 6=Cmt, 7=Paz
	    }

	    // 2) Matine mi? (12:00 oncesi)
	    public static boolean isMatinee(int saat) {
	        return saat < 12;
	    }

	    // 3) Temel fiyatı hesapla
	    // Hafta ici matine: 45 TL, Hafta ici normal: 65 TL
	    // Hafta sonu matine: 55 TL, Hafta sonu normal: 85 TL
	    public static double calculateBasePrice(int gun, int saat) {
	        boolean weekend = isWeekend(gun);
	        boolean matinee = isMatinee(saat);

	        if (!weekend && matinee) {          // hafta ici matine
	            return 45.0;
	        } else if (!weekend && !matinee) {  // hafta ici normal
	            return 65.0;
	        } else if (weekend && matinee) {    // hafta sonu matine
	            return 55.0;
	        } else {                            // hafta sonu normal
	            return 85.0;
	        }
	    }

	    // 4) Indirim oranini hesapla (0.0 - 1.0 arasi)
	    // Ogrenci: %20 (Pzt-Per), %15 (Cum-Pazar)
	    // 65+ yas: %30 (her gun)
	    // 12 yas alti: %25 (her gun)
	    // Ogretmen: %35 (sadece Carsamba)
	    public static double calculateDiscount(int yas, int meslek, int gun) {
	        double discountRate = 0.0;

	        // Yas indirimleri
	        if (yas >= 65 && 0.30 > discountRate) {
	            discountRate = 0.30;
	        }
	        if (yas < 12 && 0.25 > discountRate) {
	            discountRate = 0.25;
	        }

	        // Meslek indirimleri
	        // Ogrenci
	        if (meslek == 1) {
	            boolean weekday = !isWeekend(gun);  // 1-5
	            if (weekday) {
	                if (0.20 > discountRate) {
	                    discountRate = 0.20;
	                }
	            } else { // Cuma-Pazar -> Cum(5),Cmt(6),Paz(7)
	                if (0.15 > discountRate) {
	                    discountRate = 0.15;
	                }
	            }
	        }

	        // Ogretmen: sadece Carsamba (gun == 3)
	        if (meslek == 2 && gun == 3 && 0.35 > discountRate) {
	            discountRate = 0.35;
	        }

	        return discountRate;
	    }

	    // 5) Film formatina gore ekstra ucret
	    // 1=2D -> +0, 2=3D -> +25, 3=IMAX -> +35, 4=4DX -> +50
	    public static double getFormatExtra(int filmTuru) {
	        switch (filmTuru) {
	            case 2:
	                return 25.0;
	            case 3:
	                return 35.0;
	            case 4:
	                return 50.0;
	            case 1:
	            default:
	                return 0.0;
	        }
	    }

	    // 6) Toplam fiyat
	    public static double calculateFinalPrice(double basePrice,
	                                             double discountRate,
	                                             double formatExtra) {
	        double discountAmount = basePrice * discountRate;
	        double discountedPrice = basePrice - discountAmount;
	        return discountedPrice + formatExtra;
	    }

	    // 7) Bilet bilgisi olustur
	    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru,
	                                            double basePrice, double discountRate,
	                                            double formatExtra, double finalPrice) {

	        String dayName;
	        switch (gun) {
	            case 1: dayName = "Pazartesi"; break;
	            case 2: dayName = "Sali"; break;
	            case 3: dayName = "Carsamba"; break;
	            case 4: dayName = "Persembe"; break;
	            case 5: dayName = "Cuma"; break;
	            case 6: dayName = "Cumartesi"; break;
	            case 7: dayName = "Pazar"; break;
	            default: dayName = "Gecersiz"; break;
	        }

	        String jobName;
	        switch (meslek) {
	            case 1: jobName = "Ogrenci"; break;
	            case 2: jobName = "Ogretmen"; break;
	            case 3: jobName = "Diger"; break;
	            default: jobName = "Bilinmiyor"; break;
	        }

	        String formatName;
	        switch (filmTuru) {
	            case 1: formatName = "2D"; break;
	            case 2: formatName = "3D"; break;
	            case 3: formatName = "IMAX"; break;
	            case 4: formatName = "4DX"; break;
	            default: formatName = "Bilinmiyor"; break;
	        }

	        double discountAmount = basePrice * discountRate;

	        String info = "";
	        info += "=== SINEMA BILETI BILGISI ===\n";
	        info += String.format("Gun          : %s (%d)%n", dayName, gun);
	        info += String.format("Saat         : %d:00%n", saat);
	        info += String.format("Yas          : %d%n", yas);
	        info += String.format("Meslek       : %s%n", jobName);
	        info += String.format("Film Turu    : %s%n", formatName);
	        info += "------------------------------\n";
	        info += String.format("Temel Fiyat  : %.0f TL%n", basePrice);
	        info += String.format("Indirim Orani: %.0f%%%n", discountRate * 100);
	        info += String.format("Indirim Tutar: %.0f TL%n", discountAmount);
	        info += String.format("Format Ekstra: %.0f TL%n", formatExtra);
	        info += "------------------------------\n";
	        info += String.format("Toplam Fiyat : %.0f TL%n", finalPrice);

	        return info;
	    
	}
}



