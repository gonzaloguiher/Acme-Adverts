package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.BillboardFile;

@Component
@Transactional
public class BillboardFileToStringConverter implements Converter<BillboardFile, String> {

	@Override
	public String convert(BillboardFile o) {
		String res;

		if (o == null)
			res = null;
		else
			res = String.valueOf(o.getId());

		return res;
	}

}