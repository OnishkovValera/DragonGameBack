## Информационная система, которая позволяет взаимодействовать с объектами класса Dragon, описание которого приведено ниже:

```java
public class Dragon {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private DragonCave cave; //Поле может быть null
    private Person killer; //Поле может быть null
    private int age; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private boolean speaking;
    private DragonCharacter character; //Поле не может быть null
    private DragonHead head;
}
public class Coordinates {
    private Long x; //Максимальное значение поля: 98, Поле не может быть null
    private long y; //Значение поля должно быть больше -462
}
public class DragonCave {
    private Double depth; //Поле не может быть null
    private Double numberOfTreasures; //Поле может быть null, Значение поля должно быть больше 0
}
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле может быть null
    private long weight; //Значение поля должно быть больше 0
    private Country nationality; //Поле может быть null
}
public class DragonHead {
    private Double toothCount; //Поле может быть null
}
public class Location {
    private Double x; //Поле не может быть null
    private int y;
    private long z;
}
public enum DragonCharacter {
    CUNNING,
    GOOD,
    CHAOTIC_EVIL,
    FICKLE;
}
public enum Color {
    BLACK,
    BLUE,
    ORANGE,
    BROWN;
}
public enum Country {
    FRANCE,
    SPAIN,
    VATICAN,
    JAPAN;
}

```


Требования:

Основное назначение информационной системы - управление объектами, созданными на основе заданного в варианте класса.
 - Необходимо, чтобы с помощью системы можно было выполнить следующие операции с объектами: создание нового объекта, получение информации об объекте по ИД, обновление объекта (модификация его атрибутов), удаление объекта. Операции должны осуществляться в отдельных окнах (интерфейсах) приложения.При получении информации об объекте класса должна также выводиться информация о связанных с ним объектах.
 - При создании объекта класса необходимо дать пользователю возможность связать новый объект с объектами вспомогательных классов, которые могут быть связаны с созданным объектом и уже есть в системе.
 - Выполнение операций по управлению объектами должно осуществляться на серверной части (не на клиенте), изменения должны синхронизироваться с базой данных.
 - На главном экране системы должен выводиться список текущих объетов в виде таблицы (каждый атрибут объекта - отдельная колонка в таблице). При отображении таблицы должна использоваться пагинация (если все объекты не помещаются на одном экране).
 - Нужно обеспечить возможность фильтровать/сортировать строки таблицы, которые показывают объекты (по значениям любой из строковых колонок). Фильтрация элементов должна производиться по неполному совпадению.
 - Переход к обновлению (модификации) объекта должен быть возможен из таблицы с общим списком объектов и из области с визуализацией объекта (при ее реализации).
 - При добавлении/удалении/изменении объекта, он должен автоматически появиться/исчезнуть/измениться в интерфейсах у других пользователей, авторизованных в системе.
 - Если при удалении объекта с ним связан другой объект, связанные объекты должны удаляться.
 - Пользователю системы должен быть предоставлен интерфейс для авторизации/регистрации нового пользователя. У каждого пользователя должен быть один пароль. 
 - Требования к паролю: пароль должен быть уникален. В системе предполагается использование следующих видов пользователей (ролей):обычные пользователи и администраторы. Если в системе уже создан хотя бы один администратор, зарегистрировать нового администратора можно только при одобрении одним из существующих администраторов (у администратора должен быть реализован интерфейс со списком заявок и возможностью их одобрения).
 - Редактировать и удалять объекты могут только пользователи, которые их создали, и администраторы (администраторы могут редактировать и удалять все объекты).
 - Зарегистрированные пользователи должны иметь возможность просмотра всех объектов, но модифицировать (обновлять) могут только принадлежащие им (объект принадлежит пользователю, если он его создал). Для модификации объекта должно открываться отдельное диалоговое окно. При вводе некорректных значений в поля объекта должны появляться информативные сообщения о соответствующих ошибках.

В системе реализован отдельный пользовательский интерфейс для выполнения специальных операций над объектами:

 - Сгруппировать объекты по значению поля killer, вернуть количество элементов в каждой группе.
 - Вернуть массив объектов, значение поля head которых меньше заданного.
 - Вернуть массив объектов, значение поля speaking которых больше заданного.
 - Создать новую команду убийц драконов и сохранить её в БД.
 - Отправить команду убийц драконов в указанную пещеру.

Представленные операции реализованы в качестве функций БД

Особенности хранения объектов, которые реализованы в системе:

 - Хранение данных об объектах в реляционной СУБД (PostgreSQL). Каждый объект, с которым работает ИС, сохранен в базе данных.
 - Все требования к полям класса (указанные в виде комментариев к описанию классов) выполнены на уровне ORM и БД.
 - Для генерации поля id использованы средства базы данных.
 - Пароли при хранении хэшированы алгоритмом SHA-512.
 - При хранении объектов сохранена информация о пользователе, который создал этот объект, а также фиксировать даты и пользователей, которые обновляли и изменяли объекты.
 - Таблицы БД, не отображающие заданные классы объектов, содержат необходимые связи с другими таблицами и соответствовуют 3НФ.


Особенности организации взаимодействия с пользователем:

 - Система должна реагирует на некорректный пользовательский ввод, ограничивая ввод недопустимых значений и информируя пользователей о причине ошибки.
 - Переходы между различными логически обособленными частями системы осуществляться с помощью меню.
 - Во всех интерфейсах системы реализовано отображение информации о текущем пользователе (кто авторизован) и предоставляется возможность изменить текущего пользователя.
 - При добавлении/удалении/изменении объекта, он автоматически появляется/исчезает/измениться на области у всех других клиентов.

