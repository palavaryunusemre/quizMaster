![Logo](logo.png)
#

Bu projede android ios ve web ortamlarında geliştirilecek olan uygulamaların backend yapısıdır.

#### Proje kapsamında 2 farklı kullanıcı tipi vardır.

* ADMIN
* USER

#### Proje kapsamında 4 farklı oturum açma seçeneği vardır.
* Geleneksel kayıt sistemi
* Google ile oturum açma
* Facebook ile oturum açma
* Apple ile oturum açma

#### Chat GPT Entegrasyonu
* Proje kapsamında oluşturulacak sorular Chat GPT api'si aracılığıyla oluşturulmaktadır.
* Kullanıcıların kendi sorularını hazırlaması imkanlarıda vardır. Hazırladıkları sorular chat GPT ile kontrol edilmektedir.

#### Oda Kurma
* Kullancılar kendi odalarını kurup kendi yarışmalarını düzenleyebilirler.


## Bilgisayarınızda Çalıştırın

Bu projeyi çalıştırmak için aşağıdaki gereksinimlere sahip olmalısınız:

- **Java 17** veya daha üstü
- **Maven** 
- **Git** (Kaynak kodunu klonlamak için)

Projeyi klonlayın

```bash
  git clone https://github.com/palavaryunusemre/quizMaster.git
```

Proje dizinine gidin

```bash
  cd my-project
```

Gerekli paketleri yükleyin

```bash
  mvn clean install
```

Veritabanı Ayarları
```bash
application.properties veya application.yml dosyasındaki veritabanı ayarlarını kendi yerel veritabanınıza göre güncelleyin.
```
