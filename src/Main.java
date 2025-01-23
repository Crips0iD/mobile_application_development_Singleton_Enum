import java.util.ArrayList;
import java.util.List;

// Задача 1: Singleton для подключения к базе данных
class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Подключение к базе данных создано.");
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Подключение к базе данных успешно.");
    }
}

// Задача 2: Singleton для логирования
class Logger {
    private static Logger instance;
    private final List<String> logs = new ArrayList<>();

    private Logger() {
        System.out.println("Логирование инициализировано.");
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
        System.out.println("Лог добавлен: " + message);
    }

    public void printLogs() {
        System.out.println("Все логи:");
        logs.forEach(System.out::println);
    }
}

// Задача 3: Enum для статусов заказа
enum OrderStatus {
    NEW,
    IN_PROGRESS,
    DELIVERED,
    CANCELLED
}

class Order {
    private final String orderId;
    private OrderStatus status;

    public Order(String orderId) {
        this.orderId = orderId;
        this.status = OrderStatus.NEW;
        System.out.println("Создан новый заказ с ID: " + orderId);
    }

    public void setStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
            System.out.println("Ошибка: нельзя отменить доставленный заказ.");
            return;
        }
        this.status = newStatus;
        System.out.println("Статус заказа " + orderId + " изменён на " + status);
    }

    public OrderStatus getStatus() {
        return status;
    }
}

// Задача 4: Enum для сезонов года
enum Season {
    WINTER,
    SPRING,
    SUMMER,
    AUTUMN
}

class SeasonTranslator {
    public static String translateSeason(Season season) {
        return switch (season) {
            case WINTER -> "Зима";
            case SPRING -> "Весна";
            case SUMMER -> "Лето";
            case AUTUMN -> "Осень";
        };
    }
}

class SingletonEnumTasks {
    public static void main(String[] args) {
        // Задача 1: Проверка Singleton для базы данных
        System.out.println("=== Задача 1: Singleton для базы данных ===");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("Одинаковое подключение? " + (db1 == db2));

        // Задача 2: Проверка Singleton для логирования
        System.out.println("\n=== Задача 2: Singleton для логирования ===");
        Logger logger = Logger.getInstance();
        logger.log("Первое сообщение.");
        logger.log("Второе сообщение.");
        logger.printLogs();

        // Задача 3: Проверка Enum и Order
        System.out.println("\n=== Задача 3: Статусы заказа ===");
        Order order = new Order("12345");
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setStatus(OrderStatus.DELIVERED);
        order.setStatus(OrderStatus.CANCELLED); // Попытка отмены доставленного заказа

        // Задача 4: Проверка перевода сезонов
        System.out.println("\n=== Задача 4: Перевод сезонов ===");
        System.out.println("Весна на русском: " + SeasonTranslator.translateSeason(Season.SPRING));
        System.out.println("Зима на русском: " + SeasonTranslator.translateSeason(Season.WINTER));
    }
}