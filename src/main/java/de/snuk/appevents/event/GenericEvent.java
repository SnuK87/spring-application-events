package de.snuk.appevents.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class GenericEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {

	private static final long serialVersionUID = -2607195129341087457L;

	public GenericEvent(T entity) {
		super(entity);
	}

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getSource()));
	}

	@Override
	public String toString() {
		return source.toString();
	}
}