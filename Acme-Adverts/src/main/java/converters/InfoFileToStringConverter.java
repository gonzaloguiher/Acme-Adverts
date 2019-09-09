package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.InfoFile;

@Component
@Transactional
public class InfoFileToStringConverter implements Converter<InfoFile, String> {

	@Override
	public String convert(InfoFile o) {
		String res;

		if (o == null)
			res = null;
		else
			res = String.valueOf(o.getId());

		return res;
	}
}
