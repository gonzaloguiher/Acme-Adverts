package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SocialNetworkFile;

@Component
@Transactional
public class SocialNetworkFileToStringConverter implements Converter<SocialNetworkFile, String> {

	@Override
	public String convert(SocialNetworkFile o) {
		String res;

		if (o == null)
			res = null;
		else
			res = String.valueOf(o.getId());

		return res;
	}
}
