package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.TVFile;

@Component
@Transactional
public class TVFileToStringConverter implements Converter<TVFile, String> {

	@Override
	public String convert(TVFile o) {
		String res;

		if (o == null)
			res = null;
		else
			res = String.valueOf(o.getId());

		return res;
	}
}
