# Phân tích mô hình Client - Server: Chức năng Xem danh sách sản phẩm

## 1. Sơ đồ Luồng dữ liệu (Data Flow)

Sơ đồ dưới đây mô tả quá trình Request - Response khi Client yêu cầu xem danh sách sản phẩm từ hệ thống Spring Boot.

```mermaid
sequenceDiagram
    autonumber
    participant Client as Client (Browser/Postman)
    participant Controller as ProductController
    participant Service as ProductService
    participant Database as Database (RAM/List)

    Client->>Controller: HTTP Request (GET /api/products)
    Controller->>Service: Gọi hàm getProducts()
    Service->>Database: Trích xuất dữ liệu
    Database-->>Service: Trả về List<Product>
    Service-->>Controller: Trả về List<Product>
    Controller-->>Client: HTTP Response (Status 200 OK + JSON)
2. Phân tích chi tiết quy trình Request - Response
Để đáp ứng tính năng "Xem danh sách sản phẩm", hệ thống phân lớp sẽ hoạt động và phối hợp với nhau theo các bước sau:

A. Phía Client (Trình duyệt, Postman hoặc Mobile App)
Hành động: Người dùng/Hệ thống muốn lấy danh sách toàn bộ sản phẩm hiện có.

URL dự kiến: /api/products (Ví dụ cụ thể khi chạy local: http://localhost:8080/api/products).

HTTP Method: GET

Giải thích: Phương thức GET được sử dụng vì hành động này chỉ có tính chất "Đọc" (Read) dữ liệu, hoàn toàn không làm thay đổi hay ảnh hưởng đến trạng thái dữ liệu lưu trữ trên Server.

B. Phía Server (Spring Boot Application)
Tiếp nhận Request (Controller): Lớp ProductController đóng vai trò như một "nhân viên tiếp tân". Nhờ các annotation @RestController và @GetMapping, nó nhận diện được yêu cầu GET /api/products và kích hoạt phương thức xử lý tương ứng.

Xử lý Logic (Service): Thay vì tự xử lý, Controller sẽ "giao việc" (gọi hàm) sang ProductService. Đây là nơi chứa logic nghiệp vụ của ứng dụng. Trong tương lai, các quy tắc kinh doanh (ví dụ: chỉ lấy sản phẩm còn hàng, tính toán giảm giá...) sẽ được đặt ở đây.

C. Phía Database (Lưu trữ dữ liệu)
Vai trò: Là nơi lưu trữ dữ liệu gốc, bền vững của hệ thống.

Mô phỏng hiện tại: Trong phạm vi bài tập này, Database đang được giả lập bằng một cấu trúc dữ liệu List<Product> được cấp phát trên bộ nhớ RAM (bên trong class ProductService). Service sẽ truy cập vào List này để lấy danh sách các đối tượng.

D. Phía Response (Phản hồi trả về Client)
Định dạng dữ liệu: Server trả về định dạng JSON (JavaScript Object Notation).

Giải thích: Trong mô hình RESTful API hiện đại, JSON là chuẩn giao tiếp phổ biến nhất thay vì trả về HTML. Spring Boot sử dụng thư viện Jackson để tự động chuyển đổi danh sách các object Java (List<Product>) thành chuỗi JSON.

HTTP Status Code: 200 OK (Báo hiệu yêu cầu đã được Server tiếp nhận và xử lý thành công).

Minh họa Response Body:

JSON
[
  {
    "id": 1,
    "name": "Laptop Gaming",
    "price": 1500.0
  },
  {
    "id": 2,
    "name": "Bàn phím cơ",
    "price": 100.0
  },
  {
    "id": 3,
    "name": "Chuột không dây",
    "price": 50.0
  }
]
