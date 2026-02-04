# Чистый Код: Рефакторинг бонусной системы

## Было (в классе DirtyCode)
Один класс `LoyaltyCardService` с методом `processMonthlyBonus()` на 50+ строк:
- Смесь расчётов, логики, вывода в консоль и отправки email
- Жёсткая зависимость от `LocalDate.now()` (невозможно тестировать)
- Магические числа: 1000, 0.05, 0.03, 50 и т.д.
- Нарушение SRP: один метод делал всё

## Сделал
### 1. Разделил ответственность
9 классов вместо одного:
- `BonusCalculator` - чистые вычисления бонусов
- `DateValidator` - работа с датами (с Clock для тестов)
- `LoyaltyCardProcessor` - обработка одного клиента
- `MessageCustomerService` - формирование сообщений
- `EmailSender` - отправка email
- `LoggingService` - логирование
- `Constants` - все константы в одном месте
- `CardLevel` - enum для типобезопасности
- `LoyaltyCardService` - координация (только цикл + итоговое логирование)

### 2. Применил принципы из "Чистого кода"
- **Маленькие функции** - самый большой метод теперь 15 строк
- **Один уровень абстракции** - вычисления отделены от side effects
- **Говорящие имена** - `calculate()`, `sendNotification()`, `logSuccess()`
- **Чистые функции** - `BonusCalculator` не имеет side effects
- **Dependency Injection** - все зависимости через конструкторы
- **Неизменяемость** - все поля final

### 3. Убрал проблемы тестируемости
```java
// Было: невозможно тестировать
LocalDate.now().getMonth();

// Стало: полностью тестируемо
LocalDate.now(clock).getMonth(); // clock инжектится
```

### 4. Устранил магические числа
```java
// Было в коде:
if (amount > 1000) bonus += amount * 0.05;

// Стало:
public class Constants {
    public static final double HIGH_AMOUNT_THRESHOLD = 1000.0;
    public static final double HIGH_RATE = 0.05;
}
// Использование:
if (amount > HIGH_AMOUNT_THRESHOLD)
    bonus += amount * HIGH_RATE;
```

### Результат
-**Тестируемо**: каждый компонент тестируется изолированно
-**Поддерживаемо**: изменение логики расчёта → правка только `BonusCalculator`
-**Расширяемо**: новый канал уведомлений → новый класс, не правка монолита
-**Читаемо**: метод `processMonthlyBonus()` теперь выглядит как алгоритм:
```java
// Было в коде:
for (Customer customer : customers) {
        loyaltyCardProcessor.processLoyaltyCard(customer);
}
loggingService.logProcessedCustomersCount(customers.size());
```
### Суть
Разбил монолит на маленькие, focused классы, каждый из которых делает одну вещь и делает её хорошо. Теперь код соответствует принципам из главы "Функции" книги "Чистый Код".