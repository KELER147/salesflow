package leonardo_keler.salesflow_api_stock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Pattern(regexp = "^[0-9]{8,13}$")
    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String barcode;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costPrice;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salePrice;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 20)
    private String volume;

    private LocalDate expirationDate;

    @NotNull
    @Column(nullable = false)
    private Integer minimumStock;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private boolean active;
}
