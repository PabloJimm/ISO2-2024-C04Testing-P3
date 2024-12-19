package ISOC04.PR3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TercerProblema {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		
		// Estado de salud del usuario
		System.out.println("¿Está sano y sin síntomas (true/false)?");
		boolean estaSano = validarBoolean(scanner);

		System.out.println("¿Ha estado en contacto con alguien infectado en las últimas 2 semanas (true/false)?");
		boolean contactoConInfectado = validarBoolean(scanner);
		
		System.out.println("¿Ha pasado el covid alguna vez (true/false)?");
		boolean pasadoCovid = validarBoolean(scanner);

		System.out.println("¿Tiene el pasaporte COVID en regla (true/false)?");
		boolean tenerPasaporteCovid = validarBoolean(scanner);

		if (!estaSano || contactoConInfectado || pasadoCovid || !tenerPasaporteCovid) {
			System.out.println("No puede realizar ninguna actividad.");
			return;
		}

		// Recomendación de actividades 
		System.out.println("Introduce la temperatura (en grados Celsius):");
		int temperatura = validarTemperatura(scanner);
	
		int rangoDeTemperaturas = rangoTemperatura(temperatura);
		
		switch(rangoDeTemperaturas) {
			case -1: //Temperatura inferior a 0 grados
				System.out.println("Introduce la humedad relativa (en %):");
				int humedad = validarHumedadRelativa(scanner);
				if (humedad < 15) {
					System.out.println("¿Hay precipitaciones de nieve o agua(true/false)?");
					boolean hayPrecipitacion = validarBoolean(scanner);
					if (hayPrecipitacion) {
						System.out.println("Recomendación: Quedarse en casa.");
					} else {
						System.out.println("Introduzca el aforo máximo permitido por la legislación pertinente");
						int aforoMax = validarIntPositivo(scanner);
						System.out.println("¿Cuántas personas van a participar en la actividad?");
						int participantes = validarIntPositivo(scanner);
						if(participantes < aforoMax) {
							System.out.println("Recomendación: Ir a esquiar");
						} else {
							System.out.println("No se puede realizar la actividad debido a que se supera el aforo máximo permitido.");
						}
					}
				} else {
					System.out.println("No hay actividades recomendadas para estas condiciones, la humedad relativa tendría que ser menor a 15");
				}
				break;
			
			case 0: //Temperatura entre 0 y 15 grados
				System.out.println("¿Hay precipitaciones de nieve o agua(true/false)?");
				boolean hayPrecipitacion = validarBoolean(scanner);
				if(!hayPrecipitacion) {
					System.out.println("Introduzca el aforo máximo permitido del espacio previsto");
					int aforoMax = validarIntPositivo(scanner);
					System.out.println("¿Cuántas personas van a participar en la actividad?");
					int participantes = validarIntPositivo(scanner);
					if (participantes < aforoMax) {
						System.out.println("Recomendación: Ir a hacer senderismo");
					} else {
						System.out.println("No se puede realizar la actividad porque se supera el aforo del espacio previsto");
					}
				} else {
					System.out.println("No se puede realizar la actividad porque hay precipitaciones de nieve o de agua");
				}
				break;
				
			case 1: //Temperatura entre 15 y 25 grados
				System.out.println("¿Va a llover (true/false)?");
				boolean llueve = validarBoolean(scanner);
				System.out.println("¿Está nublado (true/false)?");
				boolean estaNublado = validarBoolean(scanner);
				System.out.println("Introduce la humedad relativa (en %):");
				humedad = validarHumedadRelativa(scanner);
				if(!llueve && !estaNublado && humedad < 60) {
					System.out.println("¿Hay restricciones de confinamiento en la ciudad (true/false)?");
					boolean restriccionesConfinamiento = validarBoolean(scanner);
					if(!restriccionesConfinamiento) {
					System.out.println("Recomendación: Ir a hacer turismo al aire libre");
					} else {
						System.out.println("No se puede realizar la actividad por restricciones de confinamiento");
					}
				} else {
					System.out.println("No puede realizar la actividad porque va a llover, está nublado o la humedad relativa es superior al 60%");
				}
				break;
				
			case 2: //Temperatura entre 25 y 35 grados
				System.out.println("¿Va a llover (true/false)?");
				llueve = validarBoolean(scanner);
				if (!llueve) {
					System.out.println("¿Cuántas personas van a participar en la actividad?");
					int participantes = validarIntPositivo(scanner);
					System.out.println("Introduzca el aforo máximo permitido del establecimiento");
					int aforoMax = validarIntPositivo(scanner);
					if(participantes < aforoMax) {
						System.out.println("Recomendación: Irse de cañas");
					} else {
						System.out.println("No se puede realizar la actividad porque hay problemas de aforo en el establecimiento");
					}
				} else {
					System.out.println("No puede realizar la actividad porque va a llover");
				}
				break;
				
			case 3: //Temperatura superior a 30 grados
				System.out.println("¿Va a llover (true/false)?");
				llueve = validarBoolean(scanner);
				if (!llueve) {
					System.out.println("Recomendación: Irse a la playa o a la piscina");
					System.out.println("En caso de ir a la playa introduzca true, en caso contrario, introduzca false");
					boolean actividad = validarBoolean(scanner);
					if (actividad == false) {
						System.out.println("Introduzca el aforo máximo permitido de la piscina");
						int aforoMax = validarIntPositivo(scanner);
						System.out.println("¿Cuántas personas van a ir a la piscina?");
						int participantes = validarIntPositivo(scanner);
						if(participantes < aforoMax) {
							System.out.println("¡Pasároslo bien en la piscina!");
						} else {
							System.out.println("No puede ir a la piscina debido a que supera el aforo máximo permitido");
						}
					} else {
						System.out.println("¡Pasaróslo bien en la playa!");
					}
				} else {
					System.out.println("No puede realizar la actividad debido a que va a llover");
				}
				break;
				
			default:
                System.out.println("No hay actividades recomendadas para estas condiciones. Intente nuevamente.");
                break;
		}
	}
	
	public static int validarTemperatura(Scanner scanner) {
	    while (true) {
	        try {
	            return scanner.nextInt(); 
	        } catch (InputMismatchException e) {
	            System.out.println("Error: Por favor, introduzca un entero.");
	            scanner.next(); 	        }
	    }
	}
	
	public static boolean validarBoolean(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduzca true o false.");
                scanner.next(); 
            }
        }
    }
    
	public static int validarIntPositivo(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value < 0) {
                    throw new IllegalArgumentException("Error: El valor no puede ser negativo. Introduzca un nuevo valor.");
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduzca un número entero válido.");
                scanner.next(); 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
	public static int validarHumedadRelativa(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value < 0) {
                    throw new IllegalArgumentException("Error: El valor no puede ser negativo. Introduzca un nuevo valor.");
                } else if (value > 100) {
                	throw new IllegalArgumentException("Error: El valor no puede ser mayor a 100. Introduzca un nuevo valor.");
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduzca un número entero válido.");
                scanner.next(); 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
	public static int rangoTemperatura(int temperatura) {
        if (temperatura < 0) {
            return -1; // Bajo 0 grados
        } else if (temperatura >= 0 && temperatura < 15) {
            return 0; // Entre 0 y 15 grados
        } else if (temperatura >= 15 && temperatura < 25) {
            return 1; // Entre 15 y 25 grados
        } else if (temperatura >= 25 && temperatura < 35) {
            return 2; // Entre 25 y 35 grados
        } else {
            return 3; // Más de 30 grados
        }
    }
}
