package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SocialNetworkFileRepository;

import domain.SocialNetworkFile;

@Component
@Transactional
public class StringToSocialNetworkFileConverter implements Converter<String, SocialNetworkFile> {

	@Autowired
	SocialNetworkFileRepository repository;

	@Override
	public SocialNetworkFile convert(String s) {
		SocialNetworkFile res;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				res = null;
			else {
				id = Integer.valueOf(s);
				res = repository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}