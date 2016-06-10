package org.sab.invsys.persistence.model.order.sales;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QSalesOrderTaxes is a Querydsl query type for SalesOrderTaxes
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrderTaxes extends EntityPathBase<SalesOrderTaxes> {

    private static final long serialVersionUID = -686280429;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QSalesOrderTaxes salesOrderTaxes = new QSalesOrderTaxes("salesOrderTaxes");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSalesOrder so;

    public final StringPath taxName = createString("taxName");

    public QSalesOrderTaxes(String variable) {
        this(SalesOrderTaxes.class, forVariable(variable), INITS);
    }

    public QSalesOrderTaxes(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrderTaxes(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrderTaxes.class, metadata, inits);
    }

    public QSalesOrderTaxes(Class<? extends SalesOrderTaxes> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.so = inits.isInitialized("so") ? new QSalesOrder(forProperty("so"), inits.get("so")) : null;
    }

}

