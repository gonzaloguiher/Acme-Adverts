package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RadioFile;

@Component
@Transactional
public class RadioFileToStringConverter implements Converter<RadioFile, String> {

	@Override
	public String convert(RadioFile o) {
		String res;

		if (o == null)
			res = null;
		else
			res = String.valueOf(o.getId());

		return res;
	}
}
