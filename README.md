# Store Management System

Store Management System, Spring Boot kullanılarak geliştirilmiş bir e-ticaret platformudur. Bu proje, müşterilerin ürünleri görüntüleyebileceği, sepete ekleyebileceği, sipariş verebileceği ve sipariş geçmişini görüntüleyebileceği bir platform sağlar.

## Başlangıç

Proje dosyalarını bilgisayarınıza klonlayın:

```bash
git clone https://github.com/kullanici_adi/proje-adı.git

```

## Gereksinimler

Bu projeyi çalıştırmak için aşağıdaki gereksinimlere ihtiyacınız olacak:

- Java JDK 8 veya üstü
- Maven
- PostgreSQL veritabanı
- Uygulamayı çalıştırın:

```bash
./mvnw spring-boot:run
```

Şimdi uygulamanız http://localhost:8080 adresinde çalışıyor olmalıdır.

## Kullanılan Teknolojiler
* Spring Boot
* PostgreSQL
* Docker
* Flyway

# Docker ile Yükleme
Projeyi Docker kullanarak çalıştırmak için aşağıdaki adımları izleyin.

- Docker üzerinde PostgreSQL başlatın:

  ```bash
  docker run --name postgres -e POSTGRES_PASSWORD=yourpassword -d -p 5432:5432 postgres

  ```

- Uygulamayı Docker içinde çalıştırın:

  ```bash
  docker build -t store-management-system .
  docker run -p 8080:8080 --name store-management-system --link postgres:postgres -d store-management-system
  ```

#  Veritabanı Yönetimi
Proje, veritabanı yönetimi için Flyway kullanmaktadır. Yapılan değişiklikler otomatik olarak uygulanır ve geri alınabilir. 
Flyway, veritabanı şemasının sürüm kontrolünü sağlar ve uygulamanın veritabanı versiyonunu güncel tutar.

```bash
> mvn flyway:migrate -Dflyway.url=... -Dflyway.user=... -Dflyway.password=...
```
# Veritabanı Yapısı

Proje, aşağıdaki tabloları içeren bir PostgreSQL veritabanı kullanır:

## sm_customer

| Sütun Adı  | Veri Tipi    | Özellikler              |
|------------|--------------|-------------------------|
| id         | bigserial    | Primary key             |
| name       | varchar(250) | Not null                |
| email      | varchar(200) | Unique, Not null        |
| address    | varchar(300) | Not null                |
| created_at | timestamp(0) | Not null                |
| updated_at | timestamp(0) |                         |
| deleted_at | timestamp(0) |                         |

## sm_product

| Sütun Adı      | Veri Tipi    | Özellikler              |
|----------------|--------------|-------------------------|
| id             | bigserial    | Primary key             |
| name           | varchar(300) | Not null                |
| price          | numeric(50,3)| Not null                |
| stock_quantity | bigserial    | Not null                |
| created_at     | timestamp(0) | Not null                |
| updated_at     | timestamp(0) |                         |
| deleted_at     | timestamp(0) |                         |

## sm_cart

| Sütun Adı   | Veri Tipi | Özellikler                  |
|-------------|-----------|-----------------------------|
| id          | bigserial | Primary key                 |
| customer_id | bigserial | Foreign key, references sm_customer |
| total_price | numeric(50,3)| Not null                |
| created_at  | timestamp(0) | Not null                |
| updated_at  | timestamp(0) |                         |
| deleted_at  | timestamp(0) |                         |

## sm_cart_item

| Sütun Adı   | Veri Tipi | Özellikler                  |
|-------------|-----------|-----------------------------|
| id          | bigserial | Primary key                 |
| cart_id | bigserial | Foreign key, references sm_cart |
| product_id  | bigserial | Foreign key, references sm_product |
| quantity    | bigserial | Not null                |
| created_at  | timestamp(0) | Not null                |
| updated_at  | timestamp(0) |                         |
| deleted_at  | timestamp(0) |                         |

## sm_order

| Sütun Adı   | Veri Tipi | Özellikler                  |
|-------------|-----------|-----------------------------|
| id          | bigserial | Primary key                 |
| customer_id | bigserial | Foreign key, references sm_customer |
| total_price | numeric(50,3)| Not null                |
| created_at  | timestamp(0) | Not null                |
| updated_at  | timestamp(0) |                         |
| deleted_at  | timestamp(0) |                         |

## sm_order_item

| Sütun Adı   | Veri Tipi | Özellikler                  |
|-------------|-----------|-----------------------------|
| id          | bigserial | Primary key                 |
| order_id | bigserial | Foreign key, references sm_cart |
| product_id  | bigserial | Foreign key, references sm_product |
| quantity    | bigserial | Not null                   |
| unit_price  | numeric(50,3)| Not null                |
| created_at  | timestamp(0) | Not null                |
| updated_at  | timestamp(0) |                         |
| deleted_at  | timestamp(0) |                         |

## sm_product_price_history

| Sütun Adı   | Veri Tipi | Özellikler                  |
|-------------|-----------|-----------------------------|
| id          | bigserial | Primary key                 |
| product_id  | bigserial | Foreign key, references sm_product |
| old_price   | numeric(50,3)| Not null                |
| new_price   | numeric(50,3)| Not null                |
| created_at  | timestamp(0) | Not null                |
| updated_at  | timestamp(0) |                         |
| deleted_at  | timestamp(0) |                         |

# Testler
Projede JUnit ve Mockito kullanılarak birim testleri bulunmaktadır. Testleri çalıştırmak için aşağıdaki komutu kullanabilirsiniz:

```bash
./mvnw test
```

# Postman API Koleksiyonu
Projeyi test etmek için Postman API koleksiyonunu kullanabilirsiniz. Aşağıdaki adımları izleyerek Postman koleksiyonunu indirebilirsiniz:

- [Postman API Koleksiyonu](https://www.postman.com/altimetry-physicist-64740135/workspace/store-management-system/collection/24190370-6015c9f8-c006-43ea-a288-2816963c2d87)
 bağlantısını tıklayarak koleksiyon dosyasını indirin.
- Postman uygulamasını açın.
- Sol üst köşedeki "Import" düğmesine tıklayın.
- "Import From Link" seçeneğini seçin ve indirdiğiniz koleksiyon bağlantısını yapıştırın.
- "Import" düğmesine tıklayarak koleksiyonu içe aktarın.
- Artık Postman'de çeşitli istekleri göndererek API'yi test edebilirsiniz.
[Postman API Koleksiyonu](https://www.postman.com/altimetry-physicist-64740135/workspace/store-management-system/collection/24190370-6015c9f8-c006-43ea-a288-2816963c2d87)

